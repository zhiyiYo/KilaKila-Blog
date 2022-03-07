/**
 * 获取网站运行时间
 * @param {string} dateString 开始日期，以 `-` 分割，比如 `2021-01-01`
 * @param {number} currentTime 当前日期
 * @returns time
 */
function getRunTime(dateString, currentTime) {
    dateString = dateString.split('-');

    let date = currentTime ? new Date(currentTime) : new Date();
    date.setUTCFullYear(dateString[0], dateString[1] - 1, dateString[2]);
    date.setUTCHours(0, 0, 0, 0);

    let birthDay = date;
    let today = new Date();
    let timeold = today.getTime() - birthDay.getTime();
    let msPerDay = 24 * 60 * 60 * 1000;
    let e_daysold = timeold / msPerDay;
    let daysold = Math.floor(e_daysold);
    let e_hrsold = (daysold - e_daysold) * -24;
    let hrsold = Math.floor(e_hrsold);
    let e_minsold = (hrsold - e_hrsold) * -60;
    let minsold = Math.floor((hrsold - e_hrsold) * -60);
    let seconds = Math.floor((minsold - e_minsold) * -60).toString();

    return {
        day: daysold,
        hour: hrsold,
        min: minsold,
        second: seconds
    };
}

export { getRunTime }