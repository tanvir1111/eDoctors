require('dotenv').config()
const express = require('express')
const app = express()
const userRouter= require('./api/users/user.router')
const doctorRouter= require('./api/doctors/doctor.router')
const appointmentRouter = require('./api/appointments/appointments.router')
const prescritionsRouter = require('./api/prescription/prescriptions.router')

app.use(express.json({limit:'50mb'}))
app.use("/images", express.static("./images"));


app.use('/api/user',userRouter)
app.use('/api/appointment',appointmentRouter)
app.use('/api/doctor',doctorRouter)
app.use('/api/prescription',prescritionsRouter)
app.get('/',()=>{
    console.log("hey");
})
app.listen(process.env.APP_PORT,()=>{
    console.log("sever running at",process.env.APP_PORT);
})
