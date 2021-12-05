const {loginDoctor,getAllDoctors,findDoctorById,findDoctorByPhone,register,resetPassword, loginWithToken,updatePicture} =require("./doctor.controller")


const router= require('express').Router()


router.get("/getAllDoctors",getAllDoctors)
router.post("/login",loginDoctor)
router.post("/findDoctorById/:bmdc",findDoctorById)
router.post("/findDoctorByPhone/:doctor_phone",findDoctorByPhone)
router.post("/register",register)
router.post("/resetPassword",resetPassword)
router.post("/login/:token",loginWithToken)
router.post("/updatePicture",updatePicture)




module.exports = router