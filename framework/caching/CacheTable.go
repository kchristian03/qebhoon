package caching

import (
	"ZenZen_API/app/cache"
	"ZenZen_API/framework/configuration"
	"ZenZen_API/framework/logging"
	"github.com/muesli/cache2go"
	"time"
)

type CacheTable struct {
	Name   string
	Table  *cache2go.CacheTable
	Config configuration.Configuration
}

func LoadCacheTable(config configuration.Configuration, logger logging.ApplicationLogger) map[string]CacheTable {

	if config.Caching.Enabled == false {
		return nil
	}
	userCacheData := cache.UserDefinedCacheTable()
	initMap := make(map[string]CacheTable)

	for _, value := range userCacheData {
		initMap[value] = CacheTable{
			Name:   value,
			Table:  cache2go.Cache(value),
			Config: config,
		}
	}

	return initMap
}

func (cacheTable CacheTable) CacheOrMake(
	key string,
	f func() (interface{}, error, time.Duration),
) (interface{}, error) {
	if cacheTable.Config.Caching.Enabled == false {
		val, err, _ := f()
		return val, err
	}

	// Simple caching function
	if cacheTable.Table.Exists(key) {
		cacheValue, cacheError := cacheTable.Table.Value(key)
		return cacheValue.Data(), cacheError
	} else {
		data, err, expiry := f()

		if err != nil {
			return nil, err
		}

		cacheTable.Table.Add(key, expiry, data)
		return data, nil
	}
}

func (cacheTable CacheTable) BustCache(key string) error {
	if cacheTable.Config.Caching.Enabled == false {
		return nil
	}
	_, err := cacheTable.Table.Delete(key)
	return err
}
