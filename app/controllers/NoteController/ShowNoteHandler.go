package NoteController

import (
	"ZenZen_API/app"
	"ZenZen_API/app/models"
	"ZenZen_API/framework/utils"
	"github.com/gofiber/fiber/v2"
)

// ShowNoteHandler
// This is an example of a handler
// Modify it to suit your needs
func ShowNoteHandler(c *fiber.Ctx) error {

	var notes []models.Note
	userID := models.User{}.LoggedInUser(c).ID

	app.DB.Where("user_id = ?", userID).Find(&notes)

	return c.Status(fiber.StatusOK).JSON(utils.HttpResponse{
		Success: true,
		Message: "Note retrieved successfully",
		Data:    notes,
	})
}
