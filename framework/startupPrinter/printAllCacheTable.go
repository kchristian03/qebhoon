package startupPrinter

import (
	"ZenZen_API/framework/caching"
	"ZenZen_API/framework/configuration"
	"github.com/TwiN/go-color"
)

func printAllCacheTable(table map[string]caching.CacheTable, config configuration.Configuration) {
	if config.Caching.Enabled {
		println("Cache table loaded:")
		for key, _ := range table {
			println(color.GreenBackground + color.Black + " [📦] " + color.Reset + " " + key)
		}
	}
}
