const {loginDoctor,getAllDoctors,findDoctorById} =require("./doctor.controller")


const router= require('express').Router()


router.get("/getAllDoctors",getAllDoctors)
router.post("/login",loginDoctor)
router.post("/findDoctorById/:doctorId",findDoctorById)


module.exports = router