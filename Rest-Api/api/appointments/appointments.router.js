const router= require('express').Router()
const {getAppointment,getDoctorAppointmentList,getPatientAppointmentList} = require('./appointments.controller')

router.post("/getAppointment",getAppointment)
router.post("/getDoctorAppointmentList/:doctor_id",getDoctorAppointmentList)
router.post("/getPatientAppointmentList/:patient_id",getPatientAppointmentList)

module.exports = router