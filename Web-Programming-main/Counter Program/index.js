const increase = document.getElementById("Increase");
const decrease = document.getElementById("Decrease");
const reset = document.getElementById("Reset");
const countLable = document.getElementById("countLable");
let count = 0;


increase.onclick = function(){
count++;
countLable.textContent = count;
}

decrease.onclick = function(){
    count--;
    countLable.textContent = count;
    }

    reset.onclick = function(){
        count=0;
        countLable.textContent = count;
        }
        
