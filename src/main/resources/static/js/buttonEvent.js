const textAreaDisplay = document.querySelector('.textArea').style.visibility;
const showCodeButton = document.getElementById('showCodeButton');
const hideCodeButton = document.getElementById('hideCodeButton');

showCodeButton.addEventListener('click', function () {
        document.querySelector('.textArea').style.visibility = "visible";
})

hideCodeButton.addEventListener('click', function () {
        document.querySelector('.textArea').style.visibility = "hidden";
})







