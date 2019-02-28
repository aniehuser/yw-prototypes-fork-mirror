package org.yesworkflow.save.data;

import org.yesworkflow.model.Channel;

public class ChannelDto
{
    public Integer id;
    public Integer outPort;
    public Integer inPort;
    public Long data;
    public Boolean isInflow;
    public Boolean isOutlfow;

    public ChannelDto(Integer id, Integer outPort, Integer inPort, Long data, Boolean isInflow, Boolean isOutlfow) {
        this.id = id;
        this.outPort = outPort;
        this.inPort = inPort;
        this.data = data;
        this.isInflow = isInflow;
        this.isOutlfow = isOutlfow;
    }

    public ChannelDto(Channel channel)
    {
        this.id = channel.id;
        this.outPort = channel.sourcePort.id;
        this.inPort = channel.sinkPort.id;
        this.data = channel.data.id;
    }

    public ChannelDto setInflow(Boolean inflow)
    {
        isInflow = inflow;
        return this;
    }

    public ChannelDto setOutlfow(Boolean outflow)
    {
        isOutlfow = outflow;
        return this;
    }
}
