package com.hundsun.px.Tools;

import java.math.BigInteger;


/**
 * 42λ��ʱ��ǰ׺+10λ�Ľڵ��ʶ+12λ��sequence���Ⲣ�������֣�12λ������ʱǿ�Ƶõ��µ�ʱ��ǰ׺��
 * <p>
 * <b>��ϵͳʱ��������Էǳ�ǿ����Ҫ�ر�ntp��ʱ��ͬ�����ܣ����ߵ���⵽ntpʱ������󣬾ܾ�����id��
 *
 */
public class IdWorker {

    private final long workerId;
    private final long snsEpoch = 1330328109047L;// ��ʼ��ǵ㣬��Ϊ��׼
    private long sequence = 0L;// 0����������
    private final long workerIdBits = 10L;// ֻ����workid�ķ�ΧΪ��0-1023
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 1023,1111111111,10λ
    private final long sequenceBits = 12L;// sequenceֵ������0-4095

    private final long workerIdShift = this.sequenceBits;// 12
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;// 4095,111111111111,12λ

    private long lastTimestamp = -1L;

    public IdWorker(long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {// workid < 1024[10λ��2��10�η�]
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {// �����һ��timestamp���²�������ȣ���sequence��һ(0-4095ѭ��)���´���ʹ��ʱsequence����ֵ
            //System.out.println("lastTimeStamp:" + lastTimestamp);
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// ��������timestamp
            }
        }
        else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            System.out.println(String.format("Clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        // ���ɵ�timestamp
        return timestamp - this.snsEpoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

    /**
     * ��֤���صĺ������ڲ���֮��
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * ���ϵͳ��ǰ������
     *
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        IdWorker iw1 = new IdWorker(1);
        IdWorker iw2 = new IdWorker(2);
        IdWorker iw3 = new IdWorker(3);

        // System.out.println(iw1.maxWorkerId);
        // System.out.println(iw1.sequenceMask);

        System.out.println("---------------------------");

        long workerId = 1L;
        long twepoch = 1330328109047L;
        long sequence = 0L;// 0
        long workerIdBits = 10L;
        long maxWorkerId = -1L ^ -1L << workerIdBits;// 1023,1111111111,10λ
        long sequenceBits = 12L;

        long workerIdShift = sequenceBits;// 12
        long timestampLeftShift = sequenceBits + workerIdBits;// 22
        long sequenceMask = -1L ^ -1L << sequenceBits;// 4095,111111111111,12λ

        long ct = System.currentTimeMillis();// 1330328109047L;//
        System.out.println(ct);

        System.out.println(ct - twepoch);
        System.out.println(ct - twepoch << timestampLeftShift);// ����22λ��*2��22�η�
        System.out.println(workerId << workerIdShift);// ����12λ��*2��12�η�
        System.out.println("����");
        System.out.println(ct - twepoch << timestampLeftShift | workerId << workerIdShift);
        long result = ct - twepoch << timestampLeftShift | workerId << workerIdShift | sequence;// ȡʱ��ĵ�40λ | ��С��1024:ֻ��12λ���ĵ�12λ | �����sequence
        System.out.println(result);

        System.out.println("---------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(iw1.nextId());
        }

        Long t1 = 66708908575965184l;
        Long t2 = 66712718304231424l;
        Long t3 = 66715908575739904l;
        Long t4 = 66717361423925248l;
        System.out.println(Long.toBinaryString(t1));
        System.out.println(Long.toBinaryString(t2));
        System.out.println(Long.toBinaryString(t3));
        System.out.println(Long.toBinaryString(t4));
        //1110110011111111011001100001111100 0001100100 000000000000
        //1110110100000010110111010010010010 0001100100 000000000000
        //1110110100000101110000111110111110 0001100100 000000000000
        //1110110100000111000101100011010000 0001100100 000000000000
        System.out.println(Long.toBinaryString(66706920114753536l));
        //1110110011111101100101110010010110 0000000001 000000000000

        String a = "0001100100";//������ֵ
        BigInteger src = new BigInteger(a, 2);//ת��ΪBigInteger����
        System.out.println(src.toString());//ת��Ϊ2���Ʋ�������

    }
}
