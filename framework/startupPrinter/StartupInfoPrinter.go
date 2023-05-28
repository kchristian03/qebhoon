package startupPrinter

import (
	"ZenZen_API/framework/caching"
	"ZenZen_API/framework/configuration"
	"ZenZen_API/framework/logging"
)

func PrintStartupInfo(
	logger logging.ApplicationLogger,
	configuration configuration.Configuration,
	cacheTable map[string]caching.CacheTable,
) {
	printAsciiArt()
	checkSecurityConfig(logger, configuration)
	checkAuthenticationConfig(configuration, logger)
	printEnabledFeatures(configuration)
	printAllCacheTable(cacheTable, configuration)
}
