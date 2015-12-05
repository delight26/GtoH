package com.bookmanage.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerAction extends HttpServlet {

	private ViewInfo view;
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public void init(ServletConfig config) throws ServletException {
		loadProperties("com/command/properties/Command");
	}

	private void loadProperties(String path) {
		ResourceBundle rbHome = ResourceBundle.getBundle(path);

		Enumeration<String> actionEnumHome = rbHome.getKeys();

		while (actionEnumHome.hasMoreElements()) {
			String command = actionEnumHome.nextElement();

			String className = rbHome.getString(command);

			try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			} catch (ClassNotFoundException e) {
				continue;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		requestPro(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		requestPro(req, res);
	}

	private void requestPro(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		if (view == null)
			view = new ViewInfo();

		CommandAction com = null;

		try {

			String command = req.getRequestURI();
			System.out.println(command);

			if (command.indexOf(req.getContextPath()) == 0) {

				command = command.substring(req.getContextPath().length());

			}
			view.setReqURI(command.substring(1));

			com = (CommandAction) commandMap.get(command);

			if (com == null) {

				System.out.println("not found : " + command);

				return;

			}
			
			view = com.requestProc(req, res, view);

			if (view.getView() == null)
				return;

		} catch (Throwable e) {

			throw new ServletException(e);

		}

		if (!view.isRedirect()) {

			RequestDispatcher dispatcher = req.getRequestDispatcher(view.getView());

			dispatcher.forward(req, res);

		} else {

			res.sendRedirect(view.getView());
			
		}
	}
}