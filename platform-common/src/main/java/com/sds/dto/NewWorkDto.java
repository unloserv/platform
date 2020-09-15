package com.sds.dto;

import com.sds.entity.Work;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@Data
public class NewWorkDto {

    @ApiModelProperty(value = "标题")
    @Length(min = 6, max = 40)
    private String title;

    private NewWorkLogDto newWorkLogDto;

    public Work trans(OnlineUser onlineUser) {
        Work work = new Work();
        work.setTitle(this.title);
        work.setCompanyId(onlineUser.getCompanyId());
        work.setStartUserId(onlineUser.getId());
        work.setStartUserName(onlineUser.getNickName());
        work.setNowUserId(this.newWorkLogDto.getToUserId());
        work.setNowUserName(this.newWorkLogDto.getToUserName());
        work.setCreateBy(onlineUser.getNickName());
        work.setCreateTime(LocalDateTime.now());
        return work;
    }

}
