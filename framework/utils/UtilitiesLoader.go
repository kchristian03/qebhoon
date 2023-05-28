package utils

import (
	"ZenZen_API/framework/configuration"
)

type BuiltinUtilities struct {
	Strings Strings
	Crypto  Crypto
}

func InitializeFrameworkUtils(config configuration.Configuration) BuiltinUtilities {
	return BuiltinUtilities{
		Strings: Strings{},
		Crypto:  Crypto{config: config},
	}
}
