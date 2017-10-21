using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace DBLibrary
{
    public class DBManage
    {
        private SqlConnection Con = null;
        
        public int CommandTimeOut { get; set; }
        public string ConStr { get; set; }

        public bool ConOpen()
        {
            try
            {
                Con = new SqlConnection(ConStr);
                Con.Open();
                return true;
            }
            catch(Exception ex)
            {
                throw (new Exception("Unable to Connect to Database"));
            }
            
        }
        
        public bool ConClose()
        {
            if (Con == null)
                return false;
            try
            {
                Con.Close();
                Con = null;
                return true;
            }
            catch(Exception ex)
            {
                throw (new Exception("Unable to Disconnect to Database"));
            }
           
        }

        public int ExecuteCommand(SqlCommand Command)
        {
            try
            {
                Command.Connection = Con;
                return Command.ExecuteNonQuery();

            }
            catch (Exception ex)
            {
                throw (ex);
            }
        }

        public SqlDataReader ExecuteReader(SqlCommand Command)
        {
            try
            {
                Command.Connection = Con;
                return Command.ExecuteReader();

            }
            catch(Exception ex)
            {
                throw (ex);
            }
        }

        public bool ExecuteCommand(ref SqlCommand Command, SqlTransaction Trans = null)
        {
            try
            {
                Command.Connection = Con;
                if (Trans != null)
                    Command.Transaction = Trans;
                Command.ExecuteNonQuery();
                return true;
            }
            catch(Exception ex)
            {
                throw (ex);
            }
        }

        public int ExecuteCommand(string SqlString, SqlTransaction Trans = null)
        {
            SqlCommand Command = null;
            if (Con == null)
                return 0;
            try
            {
                Command = new SqlCommand();
                if (Trans == null)
                    Command.Transaction = Trans;
                Command.Connection = Con;
                Command.CommandTimeout = CommandTimeOut;
                Command.CommandType = System.Data.CommandType.Text;
                Command.CommandText = SqlString;
                return Command.ExecuteNonQuery();
            }
            catch(Exception ex)
            {
                throw (ex);
            }

        }
    }
}
