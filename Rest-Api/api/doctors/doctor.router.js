const {getAllDoctors} =require("./doctor.controller")

const router= require('express').Router()


router.get("/getAllDoctors",getAllDoctors)


module.exports = router