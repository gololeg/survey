const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
});

async function showImg() {
    img.src = await toBase64(file.files[0])

}

function showAnswers() {}

function setAnswers() {
    const rows = answersTable.tBodies[0].rows;
    let arr = [];
    for (let i = 0; i < rows.length; i++) {
        let obj = {};
        obj.text = rows[i].cells[0].getElementsByTagName("textarea")[0].value
        obj.isRight = rows[i].cells[1].getElementsByTagName("input")[0].checked;
        arr[i] = obj;
    }
    strAnswers.value = JSON.stringify(arr)
}

function addAnswer() {
    let row = answersTable.insertRow(answersTable.rows.length)
    let cell0 = row.insertCell(0)
    let cell1 = row.insertCell(1)
    cell0.innerHTML = "<textarea rows='3'></textarea>"
    cell1.innerHTML = "<input type='checkbox' />"
}