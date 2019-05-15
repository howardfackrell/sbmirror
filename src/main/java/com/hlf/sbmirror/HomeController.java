package com.hlf.sbmirror;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class HomeController {

  @RequestMapping(method = RequestMethod.GET, path = "/octhc", produces = "text/plain")
  public String octhc() {
    return "All good";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/version", produces = "text/plain")
  public String version() {
    return "version: 1.0.7";
  }

  @RequestMapping(
      method = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.HEAD,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.OPTIONS,
        RequestMethod.PATCH
      },
      path = "/reflect",
      produces = "application/json")
  public Map<String, Map<String, String>> reflect(HttpServletRequest request) {
    Iterator<String> requestHeaderNames = request.getHeaderNames().asIterator();
    var headerNames = new ArrayList<String>();
    var headers = new HashMap<String, String>();
    while (requestHeaderNames.hasNext()) {
      String headerName = requestHeaderNames.next();
      headerNames.add(headerName);
      headers.put(headerName, request.getHeader(headerName));
    }

    Map<String, String> info = Map.of("uri", request.getRequestURI());
    return Map.of("headers", headers, "info", info);

  }
}
