const pool = require("../../config/db_conn");

module.exports = {

  getAllDoctors: (callback) => {
    pool.query(`select * from doctors `, (err, results) => {
      if (err) {
        return callback(err);
      }
      return callback(null, results);
    });
  },
  loginDoctor: (data,callback)=>{
    pool.query(`select * from doctors where bmdc = ? AND password = ? `,[data.bmdc,data.password], (err, results) => {
      if (err) {
        return callback(err);
      }
      console.log(results);
      return callback(null, results);
    });
  },
  findDoctorById: (data,callback)=>{
    pool.query(`select * from doctors where bmdc = ?`,[data],(err,results)=>{
      if(err){
        return callback(err)
      }
      return callback(null,results)

    })
  }
};
