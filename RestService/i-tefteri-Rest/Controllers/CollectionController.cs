using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Data.SqlClient;
using DBLibrary;
using System.Data;
using System.Configuration;
using i_tefteri_Rest.Models;

namespace i_tefteri_Rest.Controllers
{
    public class CollectionController : ApiController
    {
        //// GET: api/Collection
        //public IEnumerable<string> Get()
        //{
        //    return new string[] { "value1", "value2" };
        //}

        // GET: api/Collection/5
        public List<Collection> Get(int id)
        {
            DBManage MyDBManager = new DBManage();
            SqlCommand Command = null;
            SqlDataReader Reader = null;
            List<Collection> User_Collection = new List<Collection>();
            string SqlString = string.Empty;
            int RecsAff = 0;
            SqlString = "i_tefteri_sp_Get_Collection";
            MyDBManager.CommandTimeOut = 30;
            MyDBManager.ConStr = Constr();
            try
            {
                MyDBManager.ConOpen();
                Command = new SqlCommand();
                Command.CommandText = SqlString;
                Command.CommandType = System.Data.CommandType.StoredProcedure;
                Command.Parameters.Add("@UserID", SqlDbType.Int);
                Command.Parameters["@UserID"].Value = id;
                Reader = MyDBManager.ExecuteReader(Command);

                while (Reader.Read())
                {
                    Collection MyCollection = new Collection();
                    MyCollection.PersonID = Reader["UserID"].ToString();
                    MyCollection.PersonName = Reader["iBankuserID"].ToString();
                    MyCollection.TransDate = Reader["HMEROMHNIA"].ToString();
                    MyCollection.ActualDate = Reader["HMEROMHNIA_EKT"].ToString();
                    String.Format(MyCollection.TransAmount = Reader["POSO"].ToString(),"#,##0.00");
                    MyCollection.DOSEIS= Reader["DOSEIS"].ToString();
                    MyCollection.TransactionID= Reader["TransactionID"].ToString();
                    User_Collection.Add(MyCollection);
                    MyCollection = null;
                }
                Reader.Close();
                Reader = null;
                Command = null;
                Write2LogFile(MyDBManager, "Controller=Collection, " + "Get Users Collection", 1);
                MyDBManager.ConClose();
                MyDBManager = null;
                return User_Collection;
            }
            catch
            {
                return User_Collection;
            }
        }

        //// POST: api/Collection
        //public void Post([FromBody]string value)
        //{
        //}

        // PUT: api/Collection/5
        public void Put(int id)
        {
            if (id == 1)
            {
                //New Collection
                DBManage MyDBManager = new DBManage();
                SqlCommand Command = null;
                System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
                string userID_AGORASTH = string.Empty;
                string userID_PWLHTH = string.Empty;
                string SXOLIO_PWLHTH = string.Empty;
                string HMEROMHNIA_EKT = string.Empty;
                string Peridikotita = string.Empty;
                string POSO = string.Empty;
                string DOSEIS = string.Empty;
                int Transaction_ID = 0;
                int STATUS = 1; //ΕΚΚΡΕΜΕΙ
                int Days = 0;
                DateTime CalcDate;
                if (headers.Contains("userID_AGORASTH"))
                {
                    userID_AGORASTH = headers.GetValues("userID_AGORASTH").First();
                }
                if (headers.Contains("userID_PWLHTH"))
                {
                    userID_PWLHTH = headers.GetValues("userID_PWLHTH").First();
                }
                if (headers.Contains("SXOLIO_PWLHTH"))
                {
                    SXOLIO_PWLHTH = headers.GetValues("SXOLIO_PWLHTH").First();
                }
                if (headers.Contains("POSO"))
                {
                    POSO = headers.GetValues("POSO").First();
                }
                if (headers.Contains("DOSEIS"))
                {
                    DOSEIS = headers.GetValues("DOSEIS").First();
                }
                if (headers.Contains("HMEROMHNIA_EKT"))
                {
                    HMEROMHNIA_EKT = headers.GetValues("HMEROMHNIA_EKT").First();
                }
                if (headers.Contains("Peridikotita"))
                {
                    Peridikotita = headers.GetValues("Peridikotita").First();
                }
                string SqlString = string.Empty;
                int RecsAff = 0;
                SqlString = "i_tefteri_sp_New_Collection";
                MyDBManager.CommandTimeOut = 30;
                MyDBManager.ConStr = Constr();
                try
                {
                    switch (Peridikotita)
                    {
                        case "κάθε μήνα":
                            Days = 30;
                            break;
                        case "κάθε δυο μήνες":
                            Days = 60;
                            break;
                        case "κάθε τρεις μήνες":
                            Days = 90;
                            break;
                        case "κάθε τέσσερις μήνες":
                            Days = 120;
                            break;
                        case "κάθε εξάμηνο":
                            Days = 180;
                            break;
                        case "κάθε έτος":
                            Days = 365;
                            break;
                    }

                    MyDBManager.ConOpen();
                    Command = new SqlCommand();
                    Command.CommandText = SqlString;
                    Command.CommandType = System.Data.CommandType.StoredProcedure;
                    Command.Parameters.Add("@userID_AGORASTH", SqlDbType.Int);
                    Command.Parameters.Add("@userID_PWLHTH", SqlDbType.Int);
                    Command.Parameters.Add("@SXOLIO_PWLHTH", SqlDbType.NVarChar);
                    Command.Parameters.Add("@POSO", SqlDbType.Money);
                    Command.Parameters.Add("@DOSEIS", SqlDbType.Int);
                    Command.Parameters.Add("@STATUS", SqlDbType.Int);
                    Command.Parameters.Add("@Transaction_id", SqlDbType.BigInt);
                    Command.Parameters["@Transaction_id"].Direction = ParameterDirection.Output;
                    Command.Parameters["@userID_AGORASTH"].Value = userID_AGORASTH;
                    Command.Parameters["@userID_PWLHTH"].Value = userID_PWLHTH;
                    Command.Parameters["@SXOLIO_PWLHTH"].Value = SXOLIO_PWLHTH;
                    Command.Parameters["@POSO"].Value = Convert.ToDouble(POSO);
                    Command.Parameters["@DOSEIS"].Value = DOSEIS;
                    Command.Parameters["@STATUS"].Value = STATUS;
                    RecsAff = MyDBManager.ExecuteCommand(Command);
                    Transaction_ID = Convert.ToInt32(Command.Parameters["@Transaction_id"].Value.ToString());
                    SqlString = "i_tefteri_sp_New_Collection_DOSH";
                    CalcDate = DateTime.Parse(HMEROMHNIA_EKT);
                    System.TimeSpan duration = new System.TimeSpan(Days, 0, 0, 0);
                    for (int i = 1; i <= Convert.ToInt32(DOSEIS); i++)
                    {
                        Command = null;
                        Command = new SqlCommand();
                        Command.CommandText = SqlString;
                        Command.CommandType = System.Data.CommandType.StoredProcedure;
                        Command.Parameters.Add("@TransactionID", SqlDbType.BigInt);
                        Command.Parameters.Add("@HMEROMHNIA_EKT", SqlDbType.Date);
                        Command.Parameters.Add("@POSO", SqlDbType.Money);
                        Command.Parameters["@TransactionID"].Value = Transaction_ID;
                        Command.Parameters["@HMEROMHNIA_EKT"].Value = CalcDate;
                        Command.Parameters["@POSO"].Value = Math.Round(Convert.ToDouble(POSO) / Convert.ToInt32(DOSEIS), 2);
                        RecsAff = MyDBManager.ExecuteCommand(Command);
                        CalcDate = CalcDate.Add(duration);
                    }
                    Write2LogFile(MyDBManager, "Controller=Collection, " + "New_Collection", 2);
                }
                catch (Exception ex)
                {
                    RecsAff = Write2LogFile(MyDBManager, "Controller=Collection, " + "Error: " + ex.Message, 2);
                    MyDBManager.ConClose();
                    MyDBManager = null;
                }
            }
            else if (id == 2) //Update Collection
            {
                DBManage MyDBManager = new DBManage();
                SqlCommand Command = null;
                System.Net.Http.Headers.HttpRequestHeaders headers = this.Request.Headers;
                string Transaction_id = String.Empty;
                string SXOLIO_AGORASTH = string.Empty;
                int STATUS = 2; //ΕΓΚΡΙΘΗΚΕ ΑΠΟ ΑΓΟΡΑΣΤΗ
                int Days = 0;
                DateTime CalcDate;
                if (headers.Contains("Transaction_id"))
                {
                    Transaction_id = headers.GetValues("Transaction_id").First();
                }
                if (headers.Contains("SXOLIO_AGORASTH"))
                {
                    SXOLIO_AGORASTH = headers.GetValues("SXOLIO_AGORASTH").First();
                }
                string SqlString = string.Empty;
                int RecsAff = 0;
                SqlString = "i_tefteri_sp_Update_Collection";
                MyDBManager.CommandTimeOut = 30;
                MyDBManager.ConStr = Constr();
                try
                {
                    

                    MyDBManager.ConOpen();
                    Command = new SqlCommand();
                    Command.CommandText = SqlString;
                    Command.CommandType = System.Data.CommandType.StoredProcedure;
                    Command.Parameters.Add("@Transaction_id", SqlDbType.BigInt);
                    Command.Parameters.Add("@SXOLIO_AGORASTH", SqlDbType.NVarChar);
                    Command.Parameters["@Transaction_id"].Value = Transaction_id;
                    Command.Parameters["@SXOLIO_AGORASTH"].Value = SXOLIO_AGORASTH;
                    RecsAff = MyDBManager.ExecuteCommand(Command);
                    Write2LogFile(MyDBManager, "Controller=Collection, " + "Update_Collection", 2);
                }
                catch (Exception ex)
                {
                    RecsAff = Write2LogFile(MyDBManager, "Controller=Collection, " + "Error: " + ex.Message, 2);
                    MyDBManager.ConClose();
                    MyDBManager = null;
                }
            }
        }

        //// DELETE: api/Collection/5
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
