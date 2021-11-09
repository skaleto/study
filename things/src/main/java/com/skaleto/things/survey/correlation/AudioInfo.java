package com.skaleto.things.survey.correlation;

/**
 * @author : ybyao
 * @Create : 2019-10-22 17:37
 */
public class AudioInfo {

    private int numChannels;

    private int sampleRate;

    private int byteRate;

    private int bitsPerSample;

    public int getNumChannels() {
        return numChannels;
    }

    public AudioInfo setNumChannels(int numChannels) {
        this.numChannels = numChannels;
        return this;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public AudioInfo setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public int getByteRate() {
        return byteRate;
    }

    public AudioInfo setByteRate(int byteRate) {
        this.byteRate = byteRate;
        return this;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public AudioInfo setBitsPerSample(int bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
        return this;
    }
}
