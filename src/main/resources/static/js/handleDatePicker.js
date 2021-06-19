const submitButtonOffer = document.getElementsByName('offer');
const submitButtons = Array.from(submitButtonOffer);
const myForm = document.getElementsByName('myForm');
const myForms = Array.from(myForm);
const timePicker = document.getElementsByName('reservation');
const timePickers = Array.from(timePicker);

submitButtons[0].addEventListener('click', () =>{
    if(timePickers[0].value === "") {
        myForms[0].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[0].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[1].addEventListener('click', () =>{
    if(timePickers[1].value === "") {
        myForms[1].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[1].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[2].addEventListener('click', () => {
    if(timePickers[2].value === "") {
        myForms[2].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[2].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[3].addEventListener('click', () =>{
    if(timePickers[3].value === "") {
        myForms[3].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[3].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[4].addEventListener('click', () => {
    if(timePickers[4].value === "") {
        myForms[4].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[4].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[5].addEventListener('click', () => {
    if(timePickers[5].value === "") {
        myForms[5].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[5].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[6].addEventListener('click', () => {
    if(timePickers[6].value === "") {
        myForms[6].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[6].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[7].addEventListener('click', () => {
    if(timePickers[7].value === "") {
        myForms[7].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[7].onsubmit = () => {
            return true;
        }
    }
});

submitButtons[8].addEventListener('click', () => {
    if(timePickers[8].value === "") {
        myForms[8].onsubmit = () => {
            return false;
        }
        alert("You need to choose date before signing up the offer");
    }else{
        myForms[8].onsubmit = () => {
            return true;
        }
    }
});
