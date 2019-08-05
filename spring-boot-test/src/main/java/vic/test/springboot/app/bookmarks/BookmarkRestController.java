package vic.test.springboot.app.bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class BookmarkRestController {

    private BookmarkRepository bookmarkRepository;
    private AccountRepository accountRepository;

    @Autowired
    public BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    ResourceSupport root() {
        ResourceSupport root = new ResourceSupport();

        root.add(accountRepository.findAll().stream()
                .map(account -> linkTo(
                        methodOn(BookmarkRestController.class)
                                .readBookmarks(account.getUsername()))
                        .withRel(account.getUsername()))
                .collect(Collectors.toList()));

        return root;
    }

    @GetMapping(value = "/{userId}/bookmarks")
    Resources<BookmarkResource> readBookmarks(@PathVariable String userId) {
        validateUser(userId);

        return new Resources<>(this.bookmarkRepository
                .findByAccountUsername(userId).stream()
                .map(BookmarkResource::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/{userId}/bookmarks")
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        validateUser(userId);

        return this.accountRepository
                .findByUsername(userId)
                .map(account -> {
                    Bookmark result = bookmarkRepository.save(new Bookmark(
                            account, input.getUri(), input.getDescription()));

//                    URI location = ServletUriComponentsBuilder
//                            .fromCurrentRequest().path("/{id}")
//                            .buildAndExpand(result.getId()).toUri();
//                    return ResponseEntity.created(location).build();
                    Link forOneBookmark = new BookmarkResource(result).getLink("self");
                    return ResponseEntity.created(URI.create(forOneBookmark.getHref())).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{userId}/bookmarks/{bookmarkId}")
    BookmarkResource readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
        return validateUser(userId)
                .getBookmarks()
                .stream()
                .filter(b -> b.getId().equals(bookmarkId))
                .findFirst()
                .map(BookmarkResource::new)
                .orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));

    }

    private Account validateUser(String userId) {
        return this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

}
