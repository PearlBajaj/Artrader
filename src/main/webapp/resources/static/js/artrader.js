let params = new URLSearchParams(window.location.search);
let pathName = window.location.pathname;

// menu functions
let setSelectedHeaderMenuClass = () => {
    let menuId;
    switch (pathName) {
        case '/sale/search':
            menuId = 'headerMenuHome'; break;
        case '/user/edit':
        case '/transaction/orders':
        case '/artwork/collections':
        case '/artwork/creations':
        case '/sale/for-sale':
        case '/artwork/favourites':
        case '/transaction/shipping':
            menuId = 'headerMenuMyAccount'; break;
        default :
            return;
    }
    $('#' + menuId).addClass('active');
}

let setSelectedSideMenuClass = () => {
    let menuId;
    switch (pathName) {
        case '/user/edit':
            menuId = 'sideMenuAccount'; break;
        case '/transaction/orders':
            menuId = 'sideMenuOrders'; break;
        case '/artwork/collections':
            menuId = 'sideMenuCollections'; break;
        case '/artwork/creations':
            menuId = 'sideMenuCreations'; break;
        case '/sale/for-sale':
            menuId = 'sideMenuForSale'; break;
        case '/artwork/favourites':
            menuId = 'sideMenuMyFavourite'; break;
        case '/transaction/shipping':
            menuId = 'sideMenuShipping'; break;
        default :
            return;
    }

    $('#' + menuId).addClass('active');
}

// keyword search functions
let search = event => {
    let keyword = $('#keyword').val(),
        url = window.location.origin + '/sale/search' + (keyword === "" ? "" : '?keyword=' + keyword);

    window.location = url;
    event.preventDefault();
}

// pagination functions
let changePage = page => {
    params.set('page', page);
    window.location = '?' + params.toString();
}


// category functions
let getSelectedCategory = () => {
    return params.has('category') ? params.get('category') : 'All';
}

let changeCategory = category => {
    params.set('category', category);
    params.delete('page');
    window.location = '?' + params.toString();
}

let setCategory = () => {
    let categoryDOM = $('#dropdownCategoryLink');
    if(categoryDOM.length == 0) return;
    categoryDOM[0].innerText = 'Category: ' + getSelectedCategory();
}

$(document).ready(function(){
    setCategory();
    setSelectedHeaderMenuClass();
    setSelectedSideMenuClass();
});