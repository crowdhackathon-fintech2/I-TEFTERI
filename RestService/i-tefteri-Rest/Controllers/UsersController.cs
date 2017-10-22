using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using i_tefteri_Rest.Models;
using System.Data;
using System.Data.SqlClient;
using DBLibrary;
using System.Configuration;
using System.Web.Script.Serialization;
using Newtonsoft.Json;


namespace i_tefteri_Rest.Controllers
{
    public class UsersController : ApiController
    {
        //// GET: api/Users
        //public IEnumerable<string> Get()
        //{
        //    return new string[] { "value1", "value2" };
        //}

        // GET: api/Users/5
        public List<Person> Get(int id)
        {
            System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
            DBManage MyDBManager = new DBManage();
            SqlCommand Command = null;
            SqlDataReader Reader = null;
            List<Person> Person = new List<Person>();
            string SqlString = string.Empty;
            int RecsAff = 0;
            SqlString = "i_tefteri_sp_Get_Active_Users";
            MyDBManager.CommandTimeOut = 30;
            MyDBManager.ConStr = Constr();
            if (id == -1)
            {
                try
                {

                    if (headers.Contains("Currentuser"))
                    {
                        string user = headers.GetValues("Currentuser").First();
                    }
                    MyDBManager.ConOpen();
                    Command = new SqlCommand();
                    Command.CommandText = SqlString;
                    Command.CommandType = System.Data.CommandType.StoredProcedure;
                    Command.Parameters.Add("@What2Do", SqlDbType.VarChar, 50);
                    //Command.Parameters.Add("@Mobile_Num", SqlDbType.VarChar, 12);
                    Command.Parameters["@What2Do"].Value = "All Person";
                    //Command.Parameters["@Mobile_Num"].Value = id;
                    Reader = MyDBManager.ExecuteReader(Command);

                    while (Reader.Read()) {
                        Person MyPerson = new Person();
                        MyPerson.UserID = Convert.ToInt32(Reader["UserID"]);
                        MyPerson.iBankuserID = Reader["iBankuserID"].ToString();
                        MyPerson.BASIKOS_LOGAR = Reader["BASIKOS_LOGAR"].ToString();
                        Person.Add(MyPerson);
                        MyPerson = null;
                    }
                    Reader.Close();
                    Reader = null;
                    Command = null;
                    Write2LogFile(MyDBManager, "Controller=Users, " + "Get ALL Active Users", 1);
                    MyDBManager.ConClose();
                    MyDBManager = null;
                    //var jsonSerialiser = new JavaScriptSerializer();
                    //var json = jsonSerialiser.Serialize(Person);
                    return Person;//JsonConvert.SerializeObject(Person);
                }
                catch (Exception ex)
                {
                    Person MyPerson = new Person();
                    RecsAff = Write2LogFile(MyDBManager, "Controller=Users, " + "Error: " + ex.Message, 1);
                    MyDBManager.ConClose();
                    MyDBManager = null;
                    MyPerson.iBankuserID = "Error";
                    Person.Add(MyPerson);
                    return Person;
                }
            }
            else
            {
                try
                {
                    
                    string Currentuser = string.Empty;
                    if (headers.Contains("Currentuser"))
                    {
                        Currentuser = headers.GetValues("Currentuser").First();
                    }
                    MyDBManager.ConOpen();
                Command = new SqlCommand();
                Command.CommandText = SqlString;
                Command.CommandType = System.Data.CommandType.StoredProcedure;
                Command.Parameters.Add("@What2Do", SqlDbType.VarChar, 50);
                //Command.Parameters.Add("@Mobile_Num", SqlDbType.VarChar, 12);
                Command.Parameters["@What2Do"].Value = Currentuser;
                //Command.Parameters["@Mobile_Num"].Value = id;
                Reader = MyDBManager.ExecuteReader(Command);

                while (Reader.Read())
                {
                    Person MyPerson = new Person();
                    MyPerson.UserID = Convert.ToInt32(Reader["UserID"]);
                    MyPerson.iBankuserID = Reader["iBankuserID"].ToString();
                    MyPerson.BASIKOS_LOGAR = Reader["BASIKOS_LOGAR"].ToString();
                    Person.Add(MyPerson);
                    MyPerson = null;
                }
                Reader.Close();
                Reader = null;
                Command = null;
                Write2LogFile(MyDBManager, "Controller=Users, " + "Get 1 Active Users", 1);
                MyDBManager.ConClose();
                MyDBManager = null;
                //var jsonSerialiser = new JavaScriptSerializer();
                //var json = jsonSerialiser.Serialize(Person);
                return Person;//JsonConvert.SerializeObject(Person);
            
            }
                catch (Exception ex)
            {
                Person MyPerson = new Person();
                RecsAff = Write2LogFile(MyDBManager, "Controller=Users, " + "Error: " + ex.Message, 1);
                MyDBManager.ConClose();
                MyDBManager = null;
                MyPerson.iBankuserID = "Error";
                Person.Add(MyPerson);
                return Person;
            }
        }
        }

        //// POST: api/Users
        //public void Post([FromBody]string value)
        //{
        //}

        //// PUT: api/Users/5
        //public void Put(int id, [FromBody]string value)
        //{
        //}

        //// DELETE: api/Users/5
        //public void Delete(int id)
        //{
        //}
        private int Write2LogFile(DBManage MyDBManager, string Log_Text, int Log_Type)
        {
            SqlCommand Command = new SqlCommand();
            Command.CommandText = "i_tefteri_sp_Log_File";
            Command.CommandType = CommandType.StoredProcedure;
            Command.Parameters.Add("@What2Do", SqlDbType.VarChar, 20);
            Command.Parameters.Add("@Log_File_Type", SqlDbType.TinyInt);
            Command.Parameters.Add("@Log_File_Text", SqlDbType.NVarChar);
            Command.Parameters["@What2Do"].Value = "Insert Into Log";
            Command.Parameters["@Log_File_Type"].Value = Log_Type;
            Command.Parameters["@Log_File_Text"].Value = Log_Text;
            return MyDBManager.ExecuteCommand(Command);

        }

        private string Constr()
        {
            string MyConstr = string.Empty;
            ConnectionStringSettingsCollection settings = ConfigurationManager.ConnectionStrings;
            //return settings[0].ConnectionString;
            if (settings != null)
            {
                foreach (ConnectionStringSettings cs in settings)
                {
                    //Console.WriteLine(cs.Name);
                    //Console.WriteLine(cs.ProviderName);
                    //Console.WriteLine(cs.ConnectionString);
                    if (cs.Name == "myConnectionString")
                    {
                        MyConstr = cs.ConnectionString;
                        break;
                    }
                }
            }

            return MyConstr;
        }
    }
}
