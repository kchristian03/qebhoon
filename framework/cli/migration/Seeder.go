package migration

import (
	"ZenZen_API/app/models"
	"ZenZen_API/framework/database"
	"ZenZen_API/framework/logging"
	"github.com/TwiN/go-color"
	"gorm.io/gorm"
)

func runSeeder(logger logging.ApplicationLogger, db *gorm.DB) {

	for _, model := range models.RegisteredModels() {
		if model.Seeder != nil {
			logger.InfoLogger.Println(color.CyanBackground+color.Black+
				" [O] "+color.Reset+" Seeding table for model: ", model.Name)
			doSeeding(*model.Seeder, logger, db)
			logger.InfoLogger.Println(color.GreenBackground+color.Black+
				" [✓] "+color.Reset+" Seeded table for model: ", model.Name)
		}
	}
}

func doSeeding(seed database.SeederDefinition, logger logging.ApplicationLogger, db *gorm.DB) {
	for i := 0; i < seed.Amount; i++ {
		err := seed.Run(db)
		if err != nil {
			logger.ErrorLogger.Fatalln(err)
		}
	}
}
