package org.yesworkflow.save.response;

import org.apache.http.HttpResponse;
import org.yesworkflow.save.IYwSerializer;
import org.yesworkflow.save.data.RunDto;

public class UpdateResponse extends YwResponse<RunDto>
{
    @Override
    public YwResponse<RunDto> Build(HttpResponse response, IYwSerializer serializer)
    {
        this.build(response, serializer);
        return this;
    }

    @Override
    protected RunDto DeserializeResponseContent()
    {
        return serializer.Deserialize(this.ResponseBody, RunDto.class);
    }
}
