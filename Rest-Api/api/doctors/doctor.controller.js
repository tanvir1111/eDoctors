const {getAllDoctors,loginDoctor,findDoctorById} = require('./doctor.service')


module.exports={
    
    getAllDoctors:(req,res)=>{
        getAllDoctors((err,results)=>{
            if(err){
                return res.json({serverMsg:err.message})
            }
            if(!results){
                return res.json({
                    serverMsg:"invalid credentials"
                })
            }
            if(results.length==0){
                return res.json({serverMsg:"no doctor exists"})
            }
            else{
                return res.json(results)
            }
            
        })
    },
    loginDoctor:(req,res)=>{
       
        loginDoctor(req.body, (err,results)=>{
            if(err){
                return res.json({serverMsg:err.message})
            }
            console.log(results[0])
            if(!results){
                return res.json({
                    serverMsg:"invalid credentials"
                })
            }
            if(results.length==0){
                return res.json({serverMsg:"no doctor exists"})
            }
            else{
                return res.json(results[0])
            }
        })
    },findDoctorById:(req,res)=>{
        console.log(req.params.doctorId);
        findDoctorById(req.params.doctorId,(err,results)=>{
            if(err){
                return res.json({serverMsg:err.message})
            }
            if(!results){
                return res.json({
                    serverMsg:"invalid credentials"
                })
            }
            if(results.length>0){
                results[0].serverMsg = "Doctor found"
                return res.json(results[0])
            }
            else{
                return res.json({
                    serverMsg:"user not found"
                })
            }
            
        })
    }

}