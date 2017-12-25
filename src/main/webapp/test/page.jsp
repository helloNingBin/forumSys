<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.Member"%>
<%@page import="java.util.List"%>
<%
  List<Member> list = new ArrayList<Member>();
  Member m1 = new Member();
  m1.setName("姓名1");
  Member m2 = new Member();
  m2.setName("姓名2");
  list.add(m1);
  list.add(m2);
  pageContext.setAttribute("list", list);
%>
<c:forEach items="#{list}" var = "var">
   <h1>${var.name}</h1>
</c:forEach>
h1:::<h1 onclick="pay()">${param.name}</h1>
<script type="text/javascript">
	  alert("pay method in main");
</script>