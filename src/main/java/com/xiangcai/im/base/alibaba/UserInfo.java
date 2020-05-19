package com.xiangcai.im.base.alibaba;

import java.io.Serializable;

/**
 * @author :元放
 * @date :2020-04-28 22:55
 **/
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private Integer userId;

    private String userName;
}
