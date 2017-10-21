//using System;
//using System.Collections.Generic;
//using System.Configuration;
//using System.Linq;
//using System.Net;
//using System.Net.Http;
//using System.Web.Http;
//using DBLibrary;
//using System.Data.SqlClient;
//using System.Data;
//using i_tefteri_Rest.Models;

//namespace i_tefteri_Rest.Controllers
//{
//    public class MobileController : ApiController
//    {
//        // GET: api/Mobile
//        public IEnumerable<string> Get()
//        {
//            return new string[] { "value1", "value2" };
//        }

//        GET: api/Mobile/5
//        public Person Get(string id)
//        {
//            DBManage MyDBManager = new DBManage();
//            SqlCommand Command = null;
//            SqlDataReader Reader = null;
//            string SqlString = string.Empty;
//            int RecsAff = 0;
//            SqlString = "i_tefteri_sp_Login";
//            MyDBManager.CommandTimeOut = 30;
//            MyDBManager.ConStr = Constr();
//            try
//            {
//                MyDBManager.ConOpen();
//                Command = new SqlCommand();
//                Command.CommandText = SqlString;
//                Command.CommandType = System.Data.CommandType.StoredProcedure;
//                Command.Parameters.Add("@What2Do", SqlDbType.VarChar, 20);
//                Command.Parameters.Add("@Mobile_Num", SqlDbType.VarChar, 12);
//                Command.Parameters["@What2Do"].Value = "Find Person";
//                Command.Parameters["@Mobile_Num"].Value = id;
//                Reader = MyDBManager.ExecuteReader(Command);
//                if (Reader.Read())
//                {
//                    Person MyPerson = new Models.Person();
//                    byte[] buffer = new byte[8040];
//                    byte[] MyImage;

//                    MyPerson.Mobile_Num = id;
//                    RecsAff = Write2LogFile(MyDBManager, "TRUE, " + "Mobile: " + id, 1);
//                    MyDBManager.ConClose();
//                    MyDBManager = null;
//                    return MyPerson;
//                }
//                else
//                {
//                    Reader.Close();
//                    Reader = null;
//                    Command = null;
//                    Write2LogFile(MyDBManager, "FALSE, " + "Mobile: " + id, 1);
//                    MyDBManager.ConClose();
//                    MyDBManager = null;
//                    Person MyPerson = new Models.Person();
//                    return MyPerson;
//                }
//            }
//            catch (Exception ex)
//            {
//                RecsAff = Write2LogFile(MyDBManager, "FALSE, " + "Error: " + ex.Message, 1);
//                MyDBManager.ConClose();
//                MyDBManager = null;
//                Person MyPerson = new Models.Person();
//                return MyPerson;
//            }

//        }

//        // POST: api/Mobile
//        public void Post([FromBody]string value)
//        {
//            HttpResponseMessage response = new HttpResponseMessage();
//            response.Headers.Add("Message", "Succsessfuly Updated!!!");
//            return response;
//        }

//        // PUT: api/Mobile/5
//        public HttpResponseMessage Put([FromBody]string value)
//        {
//            HttpResponseMessage response = new HttpResponseMessage();
//            response.Headers.Add("Message", "Succsessfuly Updated!!!");
//            return response;


//        }

//        // DELETE: api/Mobile/5
//        public void Delete(int id)
//        {
//        }

//        private int Write2LogFile(DBManage MyDBManager, string Log_Text, int Log_Type)
//        {
//            SqlCommand Command = new SqlCommand();
//            Command.CommandText = "i_tefteri_sp_Log_File";
//            Command.CommandType = CommandType.StoredProcedure;
//            Command.Parameters.Add("@What2Do", SqlDbType.VarChar, 20);
//            Command.Parameters.Add("@Log_File_Type", SqlDbType.TinyInt);
//            Command.Parameters.Add("@Log_File_Text", SqlDbType.NVarChar);
//            Command.Parameters["@What2Do"].Value = "Insert Into Log";
//            Command.Parameters["@Log_File_Type"].Value = Log_Type;
//            Command.Parameters["@Log_File_Text"].Value = Log_Text;
//            return MyDBManager.ExecuteCommand(Command);

//        }

//        private string Constr()
//        {
//            string MyConstr = string.Empty;
//            ConnectionStringSettingsCollection settings = ConfigurationManager.ConnectionStrings;
//            return settings[0].ConnectionString;
//            if (settings != null)
//            {
//                foreach (ConnectionStringSettings cs in settings)
//                {
//                    Console.WriteLine(cs.Name);
//                    Console.WriteLine(cs.ProviderName);
//                    Console.WriteLine(cs.ConnectionString);
//                    if (cs.Name == "myConnectionString")
//                    {
//                        MyConstr = cs.ConnectionString;
//                        break;
//                    }
//                }
//            }

//            return MyConstr;
//        }
//    }
//}
