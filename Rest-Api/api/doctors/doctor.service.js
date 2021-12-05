const pool = require("../../config/db_conn");
const {genSaltSync,hashSync,compareSync} =require('bcryptjs')

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
    pool.query(`select * from doctors where phone = ? AND bmdc = ? `,[data.phone,data.bmdc], (err, results) => {
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
  },
  findDoctorByPhone:(data,callback)=>{
    pool.query(`select * from doctors where phone = ?`,[data.doctor_phone],(err,results)=>{
      if(err){
        return callback(err)
      }
      console.log(data.doctor_phone,results);
      return callback(null,results)
    })
  },
  register:(data,callback)=>{
    data.password = hashSync(data.password, genSaltSync(10))
    pool.query("INSERT INTO `doctors`(`first_name`,`last_name`, `bmdc`, `phone`, `speciality`, `designation`, `qualifications`, `bio`, `fee`, `password`) VALUES (?,?,?,?,?,?,?,?,?)",
    [data.first_name,data.last_name,data.bmdc,data.phone,data.speciality,data.designation,data.qualifications,data.bio,data.fee,data.password],(err,results)=>{
      if(err){
        console.log(err.message);
        return callback(err)
      }
      return callback(null,results)
    }
    )
    
  },
  resetPassword: (data,callback)=>{

    var password = hashSync(data.password,genSaltSync(10))
    pool.query("Update `doctors` set `password` = ? where `phone` = ? AND `bmdc` = ?",[password,data.phone,data.bmdc],(err,results)=>{
      if(err){
        return callback(err)
      }
      console.log(results);

      return callback(null,results)

    })

  },
  updatePicture:(data,callback)=>{
    pool.query(
    `update doctors set image_url=? where phone=? and bmdc = ?`,
      [data.image_path, data.phone,data.bmdc],
      (err, results) => {
        if (err) {
          return callback(err);
        }
        return callback(null, results);
      }
    );
  }
};
