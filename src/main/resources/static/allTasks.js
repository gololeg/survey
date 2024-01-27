function showImg(a, taskId) {
    getImg(a, taskId)
}
async function getImg(a, taskId) {
    const response = await fetch("/api/v1/tasks/" + taskId);
    const task = await response.json();
    a.parentElement.innerHTML = "<img src='" + task.imageStr + "'/>";
}