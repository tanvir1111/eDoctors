require('dotenv').config()
const express = require('express')
const app = express()
const userRouter= require('./api/users/user.router')
const doctorRouter= require('./api/doctors/doctor.router')
app.use(express.json({limit:'50mb'}))
app.use("/images", express.static("./images"));
app.use('/api/user',userRouter)
app.use('/api/doctor',doctorRouter)
app.get('/',()=>{
    console.log("hey");
})
app.listen(process.env.APP_PORT,()=>{
    console.log("sever running at",process.env.APP_PORT);
})