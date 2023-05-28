package AuthController

import (
	"ZenZen_API/app"
	"ZenZen_API/app/cache"
	models2 "ZenZen_API/app/models"
	"ZenZen_API/framework/utils"
	"github.com/gofiber/fiber/v2"
)

func LogoutHandler(c *fiber.Ctx) error {

	token := models2.User{}.LoggedInAccessToken(c)
	err := models2.PersonalAccessToken{}.RevokeToken(token)

	if err != nil {
		return c.Status(fiber.StatusInternalServerError).JSON(utils.HttpResponse{
			Success: false,
			Message: err.Error(),
			Data:    nil,
		})
	}

	// Remove the token and user from the cache
	err = app.CacheTable[cache.AuthToken].BustCache(token)

	if err != nil {
		app.Logger.ErrorLogger.Println(err)
	}

	return c.Status(fiber.StatusOK).JSON(utils.HttpResponse{
		Success: true,
		Message: "Successfully logged out",
		Data:    nil,
	})
}
