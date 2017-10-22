using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace i_tefteri_Rest.Models
{
    public class Collection
    {
        public string PersonID { get; set; }
        public string PersonName { get; set; }
        public string TransDate { get; set; }
        public string ActualDate { get; set; }
        public string TransAmount { get; set; }
        public string DOSEIS { get; set; }
        public string TransactionID { get; set; }
    }
}