const {getAllDoctors} = require('./doctor.service')


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

}