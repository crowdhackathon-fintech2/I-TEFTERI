using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace i_tefteri_Rest
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            // Web API routes
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "Main",
                routeTemplate: "itefteri/{Controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );


        }
    }
}
