<nav class="navbar navbar-expand-lg navbar-light bg-warning">
    <span class="navbar-brand mb-0 h1 ml-3">Artrader</span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse mr-3 text-light" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item" id="headerMenuHome">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item" id="headerMenuMyAccount">
                <a class="nav-link" href="/user/edit">My Account</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 mr-auto ml-auto">
            <input class="form-control mr-sm-2" type="search" placeholder="Enter keywords" aria-label="Search"
                   id="keyword"
                   value="${model.keyword}"
                   onkeypress="if(event.keyCode === 13) search(event);">
            <button class="btn btn-outline-dark my-2 my-sm-0" onclick="search(event);">Search</button>
        </form>
        Hello! ${login.name} <a class="nav-link text-dark" href="${pageContext.request.contextPath}/user/logout">Log out</a>
    </div>
</nav>