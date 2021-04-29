const {Client} = require('pg')
const client = new Client({
    user: "postgres",
    password: "baobao",
    host: "localhost",
    port: 9999,
    database: "postgres"
})
client.connect()
.then(() =>console.log("yayy"))
.then(()=>client.query("select * from Drugs"))
.then(result=>console.log(result.rows))
.then(()=>client.query("select * from Producers"))
.then(result=>console.log(result.rows))
.catch(e=>console.log(e))
.finally(()=>client.end)