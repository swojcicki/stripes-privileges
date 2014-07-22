stripes-privileges
======================
### Clone repo

```
$ git clone https://github.com/swojcicki/stripes-privileges.git
```

### Install project

```
$ cd stripes-privileges
$ mvn install
```

### Add maven dependency

```
<dependency>
  <groupId>pl.edu.agh.student.wojcicks.privileges</groupId>
  <artifactId>stripes-privileges</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### Set taglib definition

```
<%@taglib prefix="auth" uri="http://student.agh.edu.pl/~wojcicks/taglib/privileges"%>
```

### Use tags as below

```
<auth:useRigths value="UPDATE">
    <s:url var="editUrl"
           beanclass="someActionClass"
           event="edytuj">
        <s:param name="someId" value="${some.id}"/>
    </s:url>
    <o:edit url="${editUrl}"/>
</auth:useRigths>
```

### Set role interceptor in web.xml

```
<filter>
    <filter-name>StripesFilter</filter-name>
    <filter-class>net.sourceforge.stripes.controller.StripesFilter</filter-class>
    
    ..
    
    <init-param>
      <param-name>Interceptor.Classes</param-name>
      <param-value>pl.edu.agh.student.wojcicks.privileges.roles.interceptors.RolesInterceptor</param-value>
    </init-param>
    
    ..
</filter>
```

### Use annotations as below

```
@UseRights(Privileges.CREATE)
@HandlesEvent("dodaj")
public Resolution add() {
  title = "Dodaj";
  return new ForwardResolution("/jsp/some_edit.jsp");
}
```

### Catch access denied exception in handler

```
public void handleAccessDenied(AccessDeniedException throwable,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
    try {
      Resolution resolution = new RedirectResolution(HttpErrorAction.class).addParameter("code", HttpServletResponse.SC_FORBIDDEN);
      resolution.execute(request, response);
    } catch (Exception e) {
      throw e;
    }
  }
```
