<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Artrader</title>
    <%@ include file="/WEB-INF/views/tiles/include.jspf" %>
</head>
<body>
    <header id="header">
        <tiles:insertAttribute name="header" />
    </header>

    <div class="container-fluid justify-content-center align-items-center">
        <div class="row">
            <div class="col">
                <section id="site-content">
                    <tiles:insertAttribute name="body" />
                </section>
                <section id="pagination">
                    <tiles:insertAttribute name="pagination" />
                </section>
            </div>
        </div>
    </div>

</body>
</html>

