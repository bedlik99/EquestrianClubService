window.onload = () => {
    const passwordInput = document.getElementById("pass").value;
    checkFields(passwordInput,invokeAlert);
};

function checkFields(pass,alertCallback) {
    if(pass === "Password"){
        document.getElementById("pass").value = "";
        document.getElementById("username").value = "";
        document.getElementById("email").value = "";
        if(typeof alertCallback === "function") {
            alertCallback();
        }
    }
}

function invokeAlert(){
    if(document.getElementById("pass").value === ""){
        alert("Account with given Username or Email was already created." )
    }
}





