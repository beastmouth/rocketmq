/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.acl;

import java.util.List;
import org.apache.rocketmq.common.AclConfig;
import org.apache.rocketmq.common.PlainAccessConfig;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

public interface AccessValidator {

    /**
     * 从请求头中解析本次请求对应的访问资源，即本次请求需要的访问权限
     * Parse to get the AccessResource(user, resource, needed permission)
     *
     * @param request
     * @param remoteAddr
     * @return Plain access resource result,include access key,signature and some other access attributes.
     */
    AccessResource parse(RemotingCommand request, String remoteAddr);

    /**
     * 根据本次需要访问的权限，与请求用户拥有的权限进行对比验证，判断请求用户是否拥有权限，
     * 如果请求用户没有访问该操作的权限，则抛出异常，否则放行
     * Validate the access resource.
     *
     * @param accessResource
     */
    void validate(AccessResource accessResource);

    /**
     * Update the access resource config
     * 更新ACL访问控制列表的配置
     *
     * @param plainAccessConfig
     * @return
     */
    boolean updateAccessConfig(PlainAccessConfig plainAccessConfig);

    /**
     * Delete the access resource config
     * 根据账户名称删除访问授权规则
     *
     * @return
     */
    boolean deleteAccessConfig(String accesskey);

    /**
     * Get the access resource config version information
     * 获取ACL配置当前的版本号
     * @return
     */
    String getAclConfigVersion();

    /**
     * Update globalWhiteRemoteAddresses in acl yaml config file
     * 更新全局白名单IP列表
     * @return
     */
    boolean updateGlobalWhiteAddrsConfig(List<String> globalWhiteAddrsList);

    /**
     * get broker cluster acl config information
     * 获取ACL相关的配置信息
     * @return
     */
    AclConfig getAllAclConfig();
}
