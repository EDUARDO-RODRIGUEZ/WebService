package com.eduardo.web;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(serviceName = "UserService")
public class UserService {

    @Resource
    WebServiceContext wsctx;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String name) {
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("user");
        List passwordList = (List) http_headers.get("password");
        String username = "";
        String password = "";
        if (Objects.nonNull(userList) && Objects.nonNull(passwordList)) {
            username = userList.get(0).toString();
            password = passwordList.get(0).toString();
        }
        if (username.equalsIgnoreCase("eduardo") && password.equalsIgnoreCase("123")) {
            return "Hello " + name + " !";
        }
        return "unauthorized " + name + " !";
    }
}
