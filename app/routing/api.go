package routing

import (
	ExampleController2 "ZenZen_API/app/controllers/ExampleController"
	"ZenZen_API/app/controllers/NoteController"
	"github.com/gofiber/fiber/v2"
)

// ApiRoutes ---
//
// This function is used to register all the routing for the API.
// Define all of your API routing here.
func ApiRoutes(app fiber.Router) {
	app.Get("/hello", ExampleController2.HelloHandler)
	app.Get("/echo/:message", ExampleController2.EchoHandler)

	app.Post("/notes/create", NoteController.CreateNoteHandler)
	app.Get("/notes/get", NoteController.ShowNoteHandler)
	app.Put("/notes/edit/:id", NoteController.EditNoteHandler)
	app.Delete("/notes/delete/:id", NoteController.DeleteNoteHandler)
}
