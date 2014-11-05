package org.openoces.opensign.client.applet.dialogs.components;

import org.openoces.opensign.xml.nanoxml.*;

import java.io.IOException;
import java.util.*;

/**
 * Validates that a document adheres to the subset of HTML 3.2 that we allow for
 * signing operations.
 *
 * The validator requires that the input is well-formed XML, although this is
 * not a requirement in the HTML 3.2 spec.
 *
 * @see http://www.w3.org/TR/REC-html32
 */
public class HtmlValidator {
    private String reason;

	private static final Map legalTags = new HashMap(30);
	private static final Map legalBodyContent = new HashMap();

	private static final Map attributeValidators = new HashMap(10);

	static {
		addTag("html");
		addTag("body", new String[]  {"text", "bgcolor", "class", "style"}, new ContentValidator[] { null, null, null, ContentValidatorFactory.CSSContent() } );
		addTag("head");
		addTag("meta", new String[] {"http-equiv", "content"}, new ContentValidator[] { null, null, null, ContentValidatorFactory.CSSContent() } );
		addTag("title");

		addTag("p", new String[] { "align", "bgcolor", "class", "style" }, new ContentValidator[] { null, null, null, ContentValidatorFactory.CSSContent() });
		addTag("div", new String[] { "align", "bgcolor", "class", "style"}, new ContentValidator[] { null, null, null, ContentValidatorFactory.CSSContent() });
		addTag("center");

		addTag("ul", new String[] { "class", "style" });
		addTag("ol", new String[] { "start", "type", "class", "style" }, new ContentValidator[] { null, null, null, ContentValidatorFactory.CSSContent() });
		addTag("li", new String[] { "class", "style" } );

		addTags(new String[] {"h1", "h2", "h3", "h4", "h5", "h6" }, new String[] { "class", "style" }, new ContentValidator[] { null, ContentValidatorFactory.CSSContent() });

		addTag("font", new String[] {"face", "size", "color"});
		addTag("b"); addTag("i"); addTag("u");

		addTag("table", new String[] { "border", "cellspacing", "cellpadding", "width", "align" });

		addTag("tr", new String[] { "bgcolor", "class", "style" }, new ContentValidator[] { null, null, ContentValidatorFactory.CSSContent() });
		addTag("th", new String[] { "bgcolor", "rowspan", "colspan", "align", "valign", "width", "height", "class", "style" },
					 new ContentValidator[] { null, null, null, null, null, null,null, null, ContentValidatorFactory.CSSContent() });
		addTag("td", new String[] { "bgcolor", "rowspan", "colspan", "align", "valign", "width", "height", "class", "style" },
				 new ContentValidator[] { null, null, null, null, null, null,null, null, ContentValidatorFactory.CSSContent() });

		addTag("a", new String[]  { "href", "name", "class", "style" }, new ContentValidator[] { ContentValidatorFactory.internalURL(), null, null, ContentValidatorFactory.CSSContent() });

		addTag("style",ContentValidatorFactory.CSSContent(), new String[] { "type" }, new ContentValidator[] { ContentValidatorFactory.CSSType() });
	}

	private static void addTag(String tagName) {
		addTag(tagName, null);
	}

	private static void addTag(String tagName, String[] attributes) {
		if (attributes == null) {
			attributes = new String[0];
		}

		Arrays.sort(attributes); // Making binary-search possible.
		legalTags.put(tagName, attributes);
	}

	private static void addTag(String tagName, String[] attributes, ContentValidator[] contentValidators) {
		addTag(tagName,null, attributes, contentValidators);
	}

	private static void addTag(String tagName,ContentValidator bodyValidator, String[] attributes, ContentValidator[] contentValidators) {
		for (int i = 0 ; i < attributes.length ; i++) {
			if (contentValidators[i] != null) {
				attributeValidators.put(tagName + "#" + attributes[i], contentValidators[i]);
			}
		}

		addTag(tagName, attributes);

		if (bodyValidator != null) {
			legalBodyContent.put(tagName, bodyValidator);
		}
	}

	private static void addTags(String[] tagNames, String[] attributes, ContentValidator[] contentValidators) {
		for (int i = 0 ; i < tagNames.length ; i++) {
			addTag(tagNames[i], attributes, contentValidators);
		}
	}



	/**
	 * @return the reason that validation failed.
	 */
	public String getReason() {
		return reason;
	}

	/**
	 *
	 * @param html
	 * @return
	 */
	public boolean validate(String html) {
		reason = null;
		html = html.trim();

		XMLElement input;
		try {
			NanoXMLParser parser = new NanoXMLParser(true);
			NanoXMLReader reader = NanoXMLReader.stringReader(html);
			parser.setReader(reader);
			input = parser.parse();

			if (!reader.atEOF()) {
				reason = "XML Content after root.";
				return false;
			}

			return validate(input);
		} catch (XMLParseException e) {
			reason = "Malformed XML input.";
			return false;
		} catch (XMLException e) {
			reason = "Unable to validate XML";
			return false;
		} catch (IOException e) {
			reason = "Error parsing XML " + e.getMessage();
			return false;
		}
	}

	/**
	 * Traverse the input tree and verify each tag.
	 *
	 * @param input
	 * @return
	 */
	private boolean validate(XMLElement input) {
		// Content nodes do not have a name.
		if (input.getName() != null) {
			String tag = input.getName().toLowerCase();

			String[] legalAttributes = (String[]) legalTags.get(tag);
			if (legalAttributes == null) {
				reason = "HTML element '" + tag + "' not allowed.";
				return false;
			}

			for (Iterator<String> en = input.enumerateAttributeNames(); en.hasNext();) {
				String attr = en.next();
				if (Arrays.binarySearch(legalAttributes, attr) < 0) {
					reason = "Attribute '" + attr + "' is not allowed on HTML element '" + tag + "'.";
					return false;
				}

				ContentValidator attrValidator = (ContentValidator) attributeValidators.get(tag + '#' + attr);
				if (attrValidator != null) {
					String result = attrValidator.validate(input.getAttribute(attr));
					if (result != null) {
						reason = result;
						return false;
					}
				}
			}
			ContentValidator bodyValidator = (ContentValidator) legalBodyContent.get(tag);
			if (bodyValidator != null) {
				String result = bodyValidator.validate(input.getContent());
				if (result != null) {
					reason = result;
					return false;
				}
			}

		}


		List children = input.getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (!validate((XMLElement) children.get(i))) {
				return false;
			}
		}
		return true;
	}
}
