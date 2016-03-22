package com.wangcai.common.authority;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @描述: 统计在线用户
 * @author skm
 *
 */
public class EnhancedHttpSessionEventPublisher  extends HttpSessionEventPublisher {
     @Override    
     public void sessionCreated(HttpSessionEvent event) {    
         // 将用户加入到在线用户列表中
         saveOrDeleteOnlineUser(event, Type.SAVE);    
         super.sessionCreated(event);    
     }    
     
     @Override    
     public void sessionDestroyed(HttpSessionEvent event) {    
         // 将用户从在线用户列表中移除
         saveOrDeleteOnlineUser(event, Type.DELETE);    
         super.sessionDestroyed(event);    
     }    
     
     public void saveOrDeleteOnlineUser(HttpSessionEvent event, Type type) {    
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();    
        if (auth != null) {
            Object principal = auth.getPrincipal();    
            if (principal instanceof UserDetails) {
                UserDetails user = (UserDetails) principal;
                
                  switch (type) {
                    case SAVE:
                       // OnlineUserList.add(user.getId());//List<String>
                        break;
                     case DELETE:
    //                    OnlineUserList.remove(user.getId());
                         break;
                  }
             }
        }
     }
    
    /**  
    * 定义一个简单的内部枚举  
     */    
    private static enum Type {    
        SAVE, DELETE;    
    }  
}
