package AuthController

import (
	"ZenZen_API/app/models"
	"ZenZen_API/framework/utils"
	"github.com/gofiber/fiber/v2"
)

func UserHandler(c *fiber.Ctx) error {

	user := models.User{}.LoggedInUser(c)

	return c.Status(fiber.StatusOK).JSON(utils.HttpResponse{
		Success: true,
		Message: "User retrieved successfully",
		Data:    user,
	})
}
