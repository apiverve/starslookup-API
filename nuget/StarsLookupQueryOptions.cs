using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.StarsLookup
{
    /// <summary>
    /// Query options for the Stars Lookup API
    /// </summary>
    public class StarsLookupQueryOptions
    {
        /// <summary>
        /// The name of the star for which you want to get the data (e.g., Sirius)
        /// Example: Sirius
        /// </summary>
        [JsonProperty("name")]
        public string Name { get; set; }
    }
}
