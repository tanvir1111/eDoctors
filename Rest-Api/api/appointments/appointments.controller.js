const {getAppointment,getDoctorAppointmentList,getPatientAppointmentList, updateCurrentSerial,getCurrentSerial} = require('./appointments.service')

module.exports ={
    getAppointment: (req,res) =>{
        getAppointment(req.body,(err)=>{
            if(err){
                return res.json({serverMsg:err.message})
            }
        
            return res.status(200).json({serverMsg: "success"})

        })

    },
    getDoctorAppointmentList:(req,res)=>{
        getDoctorAppointmentList(req.params.doctor_id,(err,results)=>{
            if(err){
                return res.status(500).json({
                    serverMsg:err.message
                })
            }
        
        
            return res.status(200).json(results)
        })
    },
    getPatientAppointmentList:(req,res)=>{
        getPatientAppointmentList(req.params.patient_id,(err,results)=>{
            if(err){
                return res.status(500).json({
                    serverMsg:err.message
                })
            }
            return res.status(200).json(results)
        })
    },
    updateCurrentSerial: (req,res) =>{
    
        updateCurrentSerial(req.body,(err)=>{
       
            if(err){
                console.log(err.message);
                return res.status(500).json({serverMsg:err.message})
            }
        
            return res.status(200).json({serverMsg:"success"})

        })

    },
    getCurrentSerial:(req,res) =>{
        getCurrentSerial(req.params.doctor_id,(err,results)=>{
            if(err){
                return res.status(500).json({
                    serverMsg:err.message
                })
            }
        
        
            return res.status(200).json(results[0])
        })

    }

}

