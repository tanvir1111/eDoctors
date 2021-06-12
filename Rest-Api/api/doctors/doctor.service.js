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
};
