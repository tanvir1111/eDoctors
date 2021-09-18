const pool = require("../../config/db_conn");

const syncSql = require("sync-sql")


const config = {
  port:process.env.DB_PORT,
  host:process.env.DB_HOST,
  user:process.env.DB_USER,
  password:process.env.DB_PASS,
  database:process.env.DB_NAME,
 
}

module.exports = {

  getAppointment: (data,callback) => {

    pool.query(`select max(serial) as max from appointments where doctor_id=?`,[data.doctorId],(err,serialResult)=>{
        if (err) {
            return callback(err);
          }
        var serial = JSON.parse(JSON.stringify(serialResult))[0].max
        var link = data.patientId.toString() + data.doctorId.toString() + Date.now().toString()
        console.log(serial);
        if(serial==null){
            serial = 0
        } 
         pool.query(`Insert into appointments(doctor_id,patient_id,link,payment_status,date,serial) values(?,?,?,?,?,?) `,
    [
        data.doctorId,
        data.patientId,
        link,
        0,
        data.date,
        serial+1
    ]
    , (error, results) => {
      if (error) {
        return callback(error);
      }
      return callback(null, results);
    });
    })
   
  },
  getPatientAppointmentList: (patient_id,callback)=>{
      pool.query(`select * from appointments where patient_id = ?`,[patient_id],(err,results)=>{
          if(err){
              return callback(err)
          }

          resultsJson = JSON.parse(JSON.stringify(results))

          for (let i = 0; i < resultsJson.length; i++) {
             const query = `select * from doctors where bmdc = ${resultsJson[i].doctor_id}`
          var output = syncSql.mysql(config,query).data.rows;
          output[0].password = ""
          resultsJson[i].doctorData = output[0]
  
          console.log(resultsJson);
          }
         
          return callback(null,resultsJson)

      })
  },
  getDoctorAppointmentList:  (doctor_id,callback)=>{
    pool.query(`select * from appointments where doctor_id = ?`,[doctor_id],(err,results)=>{
        if(err){
            return callback(err)
        }
        resultsJson = JSON.parse(JSON.stringify(results))

        for (let i = 0; i < resultsJson.length; i++) {
           const query = `select * from users where phone = ${resultsJson[i].patient_id}`
        var output = syncSql.mysql(config,query).data.rows;
        output[0].password = ""
        resultsJson[i].patientData = output[0]

        console.log(resultsJson);
        }
       


        return callback(null,resultsJson)

    })
}
};
